resource "azurerm_resource_group" "resource_group" {
  name     = var.name
  location = var.location

  tags = var.tags

  lifecycle {
    ignore_changes = [
      tags
    ]
  }
}