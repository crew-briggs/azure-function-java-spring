data "archive_file" "file_function_app" {
  type        = "zip"
  source_dir  = var.target_path
  output_path = "${var.target_path}/../functionpackage.zip"
}
resource "azurerm_app_service_plan" "function_app_service_plan" {
  name                = "${var.function_app_name}plan"
  location            = var.location
  resource_group_name = var.resource_group_name
  kind                = var.function_app_kind
  reserved            = true

  sku {
    capacity = lookup(var.function_app_sku, "capacity", 1)
    size     = lookup(var.function_app_sku, "size", "B3")
    tier     = lookup(var.function_app_sku, "tier", "Basic")
  }

  tags = var.tags

  lifecycle {
    ignore_changes = [
      tags
    ]
  }
}

data "azurerm_storage_account_blob_container_sas" "storage_account_blob_container_sas" {
  connection_string = var.storage_account_connection_string
  container_name    = var.function_app_name
  https_only        = true
  start             = "2021-01-01T00:00:00Z"
  expiry            = "2025-01-01T00:00:00Z"

  permissions {
    read   = true
    add    = false
    create = false
    write  = false
    delete = false
    list   = false
  }
}

resource "azurerm_storage_container" "function_app_code_container" {
  name                 = var.function_app_name
  storage_account_name = var.storage_account_name
}

resource "azurerm_storage_blob" "storage_blob" {
  name                   = "${filesha256(data.archive_file.file_function_app.output_path)}.zip"
  storage_account_name   = azurerm_storage_container.function_app_code_container.storage_account_name
  storage_container_name = azurerm_storage_container.function_app_code_container.name
  type                   = "Block"
  source                 = data.archive_file.file_function_app.output_path
}

resource "azurerm_function_app" "function_app" {
  name                       = var.function_app_name
  resource_group_name        = var.resource_group_name
  location                   = var.location
  app_service_plan_id        = azurerm_app_service_plan.function_app_service_plan.id
  storage_account_name       = azurerm_storage_container.function_app_code_container.storage_account_name
  storage_account_access_key = var.storage_account_key
  os_type                    = var.function_app_kind == "Linux" ? "linux" : ""
  version                    = "~${var.function_app_version}"
  https_only                 = true
  app_settings = merge({
    FUNCTIONS_WORKER_RUNTIME       = "java"
    WEBSITE_RUN_FROM_PACKAGE       = "https://${var.storage_account_name}.blob.core.windows.net/${var.function_app_name}/${azurerm_storage_blob.storage_blob.name}${data.azurerm_storage_account_blob_container_sas.storage_account_blob_container_sas.sas}"
  }, var.additional_app_settings)

  site_config {
    always_on                 = false
    ftps_state                = "Disabled"
    http2_enabled             = true
    min_tls_version           = 1.2
    use_32_bit_worker_process = false
    linux_fx_version          = "JAVA|11"

    cors {
      allowed_origins = var.cors_allowed_origins
    }
  }

  identity {
    type = "SystemAssigned"
  }

  tags = var.tags

  lifecycle {
    ignore_changes = [
      tags
    ]
  }
}