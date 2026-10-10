#!/usr/bin/env python3

import argparse, random, string, sys

def obtenerSimbolosPuntuacion():
    ''' Funcion que devuelve los simbolos validos para la contraseña'''

    # string.punctuation contiene todos los simbolos de puntuacion
    simbolos = string.punctuation.replace('"', '') #los simbolos validos son los de string.punctuation menos las comillas dobles
    simbolos = simbolos.replace("'", '')
    return simbolos

# FUNCION DEL APARTADO 3.4.1
def esValida(contrasenha):
    '''Funcioon que devuelve true si la contraseña es valida siguiendo los criterios del enunciado'''
    simbolos = obtenerSimbolosPuntuacion() 

    # primera condicion: minimo 8 caracteres (si tiene menos false)
    if len(contrasenha) < 8: 
        return False

    # segunda condicion: sin " ni ' ni espacios en blanco
    if '"' in contrasenha or "'" in contrasenha or ' ' in contrasenha:
        return False

    # tercera condicion: al menos una mayuscula, una minuscula, un numero y un simbolo
    # contadores de cada tipo
    mayus = 0
    minus= 0
    num = 0
    simb = 0
    letras = 0

    # recorrer la contraseña caracter a caracter
    for char in contrasenha:
        if char.isupper(): # si es mayuscula
            mayus += 1
            letras += 1
            if contrasenha.count(char) > 2: # si hay mas de 2 veces el mismo caracter, no es valida
                return False
            
        elif char.islower(): # si es minuscula
            minus += 1
            letras += 1

            if contrasenha.count(char) > 2: # si hay mas de 2 veces el mismo caracter, no es valida
                return False
            
        elif char.isdigit(): # si es numero
            num += 1

        elif char in simbolos: # si es simbolo de puntuacion
            simb += 1

    # Condicion: Como minimo 1 letra mayuscula, 1 minuscula, 1 numero y 1 simbolo
    if mayus < 1 or minus < 1 or num < 1 or simb < 1:
        return False

    # Condicion: Como minimo 4 letras
    if letras < 4:
        return False

    # Si pasa todas las comprobaciones, es valida
    return True

def comprobarContrasenha(numero, archivo = None):
    ''' Lee el numnero de contraseñas pasado por comandos y comprueba si son validas'''
    if archivo is not None: # si se pasa un archivo se leen numero de contraseñas del archivo y se comprueban
        try:
            archivo = open(archivo, 'r') #abrir archivo en modo lectura
            lineas = archivo.readlines() #leer las lineas
            archivo.close()

            # Recorremos solo el numero de contraseñas que nos piden (-c num)
            for i in range(numero):
                if i < len(lineas):
                    contrasenha = lineas[i].strip() # quitar el \n y los espacios en blanco al principio y al final de la linea
                    if esValida(contrasenha):
                        print(contrasenha, "SI")
                    else:
                        print(contrasenha, "NO")
                        
        except IOError: # si no se puede abrir el archivo, se lanza un error y se sale del programa
            print("Error al abrir el archivo")
            sys.exit(1)
            
    else:
        for i in range(numero):
            linea = input()
            contrasenha = linea.strip() # quitar espacios en blanco al principio y al final y los \n 

            if esValida(contrasenha):
                print(contrasenha, "SI")
            else:
                print(contrasenha, "NO")

# FUNCIONES PARA EL 3.4.2
def generarContrasenhaAleatoria():
    ''' Genera una cadena aleatoria de entre 8 y 12 caracteres hasta que se cumplan los requisitos'''
    simbolos = obtenerSimbolosPuntuacion() #obtener los simbolos validos
    caracteresDisponibles = string.ascii_letters + string.digits + simbolos 

    # bucle inf hasta encontrar cadena valida
    while True:
        longitud = random.randint(8, 12) # longitud aleatoria entre 8 y 12
        contrasenha = "" # se inicializa la cadena vacia
        #generar cadena caracter a caracter
        for c in range(longitud):
            contrasenha = contrasenha + random.choice(caracteresDisponibles) # se añade un caracter aleatorio de los disponibles

        if esValida(contrasenha): #comprobar y retornar si es valida
            return contrasenha

def generarContrasenhasAleatorias(numero, archivo = None):
    ''' Genera 'numero' de contraseñas aleatorias'''
    if archivo is not None: # si se pasa un archivo, se escriben las contraseñas en el archivo
        try:
            f = open(archivo, 'w') #abrirlo en modo escritura
            for i in range(numero): # una por cada 'numero' de contraseñas
                f.write(generarContrasenhaAleatoria() + '\n') # se genera y se añade al archivo, seguida de un \n para que la siguiente sea una nueva linea
            f.close() # se cierra

        except IOError:
            print("No puedo escribir en el archivo")
            sys.exit(1)
    else:
        for i in range(numero):
            print(generarContrasenhaAleatoria())


def main():
    # definir un parseador
    parseador = argparse.ArgumentParser(description='Comprueba si las contraseñas son validas')

    # crear grupo mutualmente excluyente 
    # Grupo exclusivo de argparse: evita que se usen -c y -g a la vez, lanza un error si se hace
    grupo = parseador.add_mutually_exclusive_group() 
    grupo.add_argument('-c', type=int, help='Número de contraseñas a comprobar')
    grupo.add_argument('-g', type=int, help='Numero de contraseñas aleatorias a generar')
    # parseador.add_argument('-c', type=int, help='Número de contraseñas a comprobar') #argumento opcional: el número de contraseñas a comprobar
    # parseador.add_argument('-g', type=int, help='Numero de contraseñas a generar aleatorias') #argumento opcional: el número de contraseñas a generar
    parseador.add_argument('-f', help='Archivo') # argumento para dar nombre del archivo

    args = parseador.parse_args() # recogemos los valores introducidos

    if args.c is not None: #si no esta vacio el argumento -c, se comprueban las contraseñas
        comprobarContrasenha(args.c, args.f)
    elif args.g is not None: #si no esta vacio el argumento -g, se generan contraseñas aleatorias
        generarContrasenhasAleatorias(args.g, args.f)
    elif args.c is None and args.g is None:
        generarContrasenhasAleatorias(1) #si no se pasa ningun argumento, se genera una contraseña aleatoria

if __name__ == "__main__":
    main()