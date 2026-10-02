#!/bin/bash

# se guardan en variables las rutas pasadas por comandos al ejecutar 
disc1=$1
disc2=$2
malas=$3

# se usa awk para poner el archivo en mayusculas y ordenado como debe ser la salida
# se usa toupper para poner en mayusculas el campo 5 (titulo), un espacio y el campo 3 (peso)
# y se imprime el un texto fijo "DISCO1" o "DISCO2"
# tambuien se indica despues del codigo de awk los archivos de los que se lee
disco1=$(awk '{print toupper($5 " " $3), "DISCO1"}' "$disc1")
disco2=$(awk '{print toupper($5 " " $3), "DISCO2"}' "$disc2")

# se juntan los contenidos de las 2 variables, se pone el enter para que se separen las lineas entre las variables
# se usa grep para filtrar las lineas que no se quieren (las que estan en malas.txt)
# -v (las busquedas que no coincidan) -i (ignora el case) 
# -w (coincidencia de palabra completa) -f (archivo con las lineas a filtrar)
echo "$disco1
$disco2" | grep -v -i -w -f "$malas" | sort

# se podria redirigir la salida a un archivo de texto con > nombreArchivo.txt tanto despues del sort
# como al ejecutarlo en la terminal despues de los 3 argumentos