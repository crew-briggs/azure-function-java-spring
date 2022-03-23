variable "name" {
  description = "resource group name"
  type        = string
}

variable "location" {
  description = "location of resource group"
  type        = string
}

variable "tags" {
  description = "resource group tags"
  type        = map(string)
  default     = {}
}

