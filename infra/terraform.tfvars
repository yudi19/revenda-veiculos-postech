# Região AWS
aws_region = "us-east-1"

# Tipo de instância EC2 (t4g.micro é ARM e elegível para free tier)
instance_type = "t4g.micro"

# Chave pública SSH - Gere com: ssh-keygen -t rsa -b 4096 -f ~/.ssh/revenda-key
# Cole aqui o conteúdo do arquivo ~/.ssh/revenda-key.pub
public_key = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQDbKz/ncLY6FQ2awB7dm8mf6h2qMVNNXVm7cl+sbg72vEm06mZjKvhMfNBqa5m73e0zGlkU7omWZbuSKCp1Sb8ehMX2NqTfKGvfEzJQ171XywqjZwv2/qgaCGx2BAO//ZT/zLCi0UE+NX/VkVK5O4huI6dnpwHGEYyBIF87ns2zuBPyXGRAQEL7evShZ1FO1jvJfASV9N+LzT1sNj4dzg5TqsP4yvcI44ZGOjM0m5Hl0ivsePls+z5AcT0Vrzfu4zN7bLnitFOlo8unSlz8M6N0KSWjWTiyrm3eTWXhBDQzoSrL1YzcjrUJOKFhPrI+wcMYhvsLw08n07NbpnV5NbDvmdjWDqsOFk6ZGazKzzg9HcYD+RGoVBFKgyPfsTiclXXM7kZ6QgFNYuJ7x1RzqXicpyrIazEByBT9P8LUSXyXlWo5sDbhVy52qY+PJ94WA9+JfWHJJ4XDs7Qc6eqzxDXX5Hj/90Ynfd5+RZfkoxvAdQVTJF6fD22MCBiq0wJpB6PF1GWETf1TgX4lEtaWz5veQQRt8jGVL7G0W0niawFvp165QvUtEByMUmqFiZQqaxwidTL850LP1YmVCL/X17g/WnUHEVjr8vB28BYSvzPyAjbbnhLmL6iodv2ayRaMdq43X2CucGjlDyNVS0J4Y9oYHoh9I1boxD6COLXPhIC2mQ== fabio@Fabio"

# CIDR para acesso SSH - Troque 0.0.0.0/0 pelo seu IP público para maior segurança
# Ex: "203.0.113.0/32" (apenas seu IP)
ssh_cidr = "0.0.0.0/0"
