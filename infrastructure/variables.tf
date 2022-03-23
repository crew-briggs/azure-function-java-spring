variable "project_name" {
  description = "project name"
  type        = string
}

variable "environment_name" {
  description = "environment to be deployed"
  type        = string
  default     = "dev"
}

variable "iteration" {
  description = "deployment designation"
  type        = string
  default     = "1"
}

variable "location" {
  description = "location of deployed resources"
  type        = string
}