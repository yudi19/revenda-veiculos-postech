variable "aws_region" {
  description = "Região da AWS onde os recursos serão criados"
  type        = string
  default     = "us-east-1"
}

variable "instance_type" {
  description = "Tipo da instância EC2"
  type        = string
  default     = "t4g.micro"
}

variable "public_key" {
  description = "Chave pública SSH para acesso à instância EC2"
  type        = string
}

variable "ssh_cidr" {
  description = "CIDR permitido para acesso SSH (recomendado: seu IP público)"
  type        = string
  default     = "0.0.0.0/0"
}
