data "azurerm_client_config" "current" {}

locals {
  unique_name = "${var.project_name}${var.environment_name}${var.iteration}"
}

module "resource_group" {
  source   = "./modules/resource-group"
  name     = "${local.unique_name}rg"
  location = var.location
}

module "storage_account" {
  source              = "./modules/storage"
  name                = "${local.unique_name}store"
  location            = module.resource_group.location
  resource_group_name = module.resource_group.name
}

module "spring_function_app" {
  source                            = "./modules/azure-function"
  function_app_name                 = "${local.unique_name}func"
  location                          = module.resource_group.location
  resource_group_name               = module.resource_group.name
  storage_account_name              = module.storage_account.name
  storage_account_key               = module.storage_account.account_key
  storage_account_connection_string = module.storage_account.connection_string
  target_path                       = "../target/azure-functions/spring-api"
}
