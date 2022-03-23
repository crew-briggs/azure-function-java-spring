variable "function_app_name" {
  description = "function name"
  type        = string
}

variable "location" {
  description = "Location"
  type        = string
}

variable "resource_group_name" {
  description = "Resource group name"
  type        = string
}

variable "function_app_kind" {
  description = "function app kind"
  type        = string
  default     = "Linux"
}

variable "storage_account_name" {
  description = "storage account name for function app"
  type        = string
}

variable "storage_account_connection_string" {
  description = "Connection string for storage account."
  type        = string
}

variable "storage_account_key" {
  description = "Key for the storage account"
  type        = string
}

variable "function_app_sku" {
  description = "app service sku. consists of capacity, size and tier "
  type        = map(string)
  default = {
    "capacity" : 1
    "tier" : "Basic",
    "size" : "B1"
  }
}

variable "function_app_version" {
  description = "function app version"
  type        = number
  default     = 4
}

variable "target_path" {
  description = "built and packaged function code."
  type        = string
}

variable "cors_allowed_origins" {
  description = "A list of allowed cors origins."
  type        = list(string)
  default     = ["http://localhost:3000"]
}

variable "tags" {
  description = "tags to describe the deployed function"
  type        = map(string)
  default     = {}
}

variable "additional_app_settings" {
  description = "tags for function app"
  type        = map(string)
  default     = {}
}
