output "name" {
  description = "outputs the name associated with resource group"
  value       = azurerm_resource_group.resource_group.name
}

output "location" {
  description = "outputs the Azure region associated with resource group"
  value       = azurerm_resource_group.resource_group.location
}