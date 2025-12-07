output "instance_public_ip" {
  description = "IP público da instância EC2"
  value       = aws_instance.revenda_api.public_ip
}

output "instance_id" {
  description = "ID da instância EC2"
  value       = aws_instance.revenda_api.id
}

output "ami_id" {
  description = "ID da AMI utilizada"
  value       = data.aws_ami.amazon_linux_2023_arm64.id
}
