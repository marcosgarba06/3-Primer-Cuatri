#!/bin/bash

# ^ indica que empiece desde el inicio de la linea
# (.*[^0-9]) primer grupo de captura, cualquier caracter que no sea un numero y con el * se indica que se puede repetir 0 o mas veces
# ([0-9]+) segundo grupo, cualquier caracter que sea un numero y con el + indica que se puede repetir 1 o mas veces
# ([^0-9]+) tercer grupo, cualquier caracter que no sea un numero
# \.epub$ indica que termine con la extension .epub
# & indica el patron completo encontrado (nombre del arhivo)
# con los numeros y los - se indica el nuevo nombre
sed -r 's/^(.*[^0-9])([0-9]+)([^0-9]+)\.epub$/mv & \3-\1-\2.epub/g'