output "name" {
  description = "Name of the Storage Account"
  value       = azurerm_storage_account.storage_account.name
}

output "connection_string" {
  description = "connection string for Storage Account"
  value       = azurerm_storage_account.storage_account.primary_connection_string
  sensitive   = true
}

output "account_key" {
  description = "primary key of storage account"
  value       = azurerm_storage_account.storage_account.primary_access_key
  sensitive   = true
}