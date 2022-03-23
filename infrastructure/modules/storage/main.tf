resource "azurerm_storage_account" "storage_account" {
  name = var.name

  location            = var.location
  resource_group_name = var.resource_group_name

  account_replication_type = var.account_replication_type
  account_tier             = var.account_tier
  account_kind             = var.account_kind
  min_tls_version          = var.min_tls_version

  enable_https_traffic_only = var.enable_https_traffic_only
  allow_blob_public_access  = var.allow_blob_public_access

  tags = var.tags

  lifecycle {
    ignore_changes = [
      tags
    ]
  }
}