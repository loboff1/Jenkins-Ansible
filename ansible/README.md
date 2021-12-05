Ansible Playbook with roles
=========

Playbook for installing Apache2 with PHP mod and creating demo-page on PHP


Role Variables
--------------

The folder /defaults contains variables for setting up configuration of your PHP website

user: A remote non-root user for the Ansible host that will be configured as the owner of the application files.

host: your domain name.

config: the name of the configuration file to be created in Apache.

port: The HTTP port for this virtual host, 80 is the default.

disable_default: Whether to disable the default Apache website.


Role Files
--------------

The folder /files contains jinja2-templates:

phpinfo.php.j2 - demo php-page that will copy to the new directory var/www/{{ http_host }} 

website.conf.j2 - template that configures the new VirtualHost in Apache. The variables used in this template are defined in the variables as {{ http_port }} 


Running encrypted playbook
--------------

The playbook is encrypted.

Command for running at command line:

    ansible-playbook playbook-roles.yaml --ask-vault-pass

Password ask at author's playbook.

Author Information
--------------

Author: Andrey

