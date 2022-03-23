variable "location" {
  description = "Azure storage account location"
  type        = string
}

variable "resource_group_name" {
  description = "Resource group name"
  type        = string
}

variable "account_replication_type" {
  description = "storage account replication type (potential values - LRS, ZRS, GRS)"
  type        = string
  default     = "ZRS"
}

variable "account_tier" {
  description = "storage account tier ( ppotential values - Standard, Premium)"
  type        = string
  default     = "Standard"
}

variable "name" {
  description = "Name of the Storage account "
  type        = string
}

variable "account_kind" {
  description = "Storage Account Kind"
  type        = string
  default     = "StorageV2"
}

variable "min_tls_version" {
  description = "Storage Account minimal TLS version"
  type        = string
  default     = "TLS1_2"
}

variable "enable_https_traffic_only" {
  description = "Boolean flag to enable https traffic only option"
  type        = bool
  default     = true
}

variable "allow_blob_public_access" {
  description = "Boolean flag to enable public access to the blob"
  type        = bool
  default     = false
}

variable "tags" {
  description = "tags to add to Storage Account"
  type        = map(string)
  default     = {}
}
