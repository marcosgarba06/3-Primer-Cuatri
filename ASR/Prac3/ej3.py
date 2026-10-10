#!/usr/bin/env python3

import sys, argparse

def estaBalanceado(cad):
    '''Comprueba si una cadena de paréntesis está balanceada'''
    sum = 0 # Se inicializa la variable sum a 0
    # se recorre la cadena sumando 1 si es ( y restando 1 si es ) como explica el enunciado
    for c in cad: 
        if c == '(': 
            sum += 1 
        elif c == ')':
            sum -= 1
        # si en algun momento la suma es negativa no esta balanceada como explica el enunciado
        if sum < 0: 
            return False
    
    # retornar true o false segun corresponda (balanceada si es 0)
    if sum != 0:
        return False
    else:
        return True

def leerArchivo(nArch):
    '''Lee un archivo pasado por comandos linea por linea'''
    try:
        f = open(nArch, 'r') # abrir archivo para lectura
    except IOError:
        print("No se pudo abrir el archivo")
        sys.exit(1) # salir del programa si no se puede abrir el archivo 
    else:
        linArchList = f.readlines() # leer lineas del archivo y aklmacenarlas en una lista
        f.close() 
        
        # quitar el retorno de línea (\n) de cada línea
        lineasStrip = []
        for l in linArchList:
            lineasStrip.append(l.strip())
        return lineasStrip     


def comprobarBalance(listaArchivo):
    '''Comprueba si las lineas de un archivo estan balanceadas'''
    for l in listaArchivo:
        if estaBalanceado(l):
            print(l, "SI")
        else:
            print(l, "NO")

def main():
    # definir un parseador
    parsear = argparse.ArgumentParser(description='Comprueba si las secuencias son balanceadas')
    parsear.add_argument('fichero', help='nombre del fichero') # argumento posicional: el fichero
    args = parsear.parse_args() # recogemos los valores introducidos
    lineas = leerArchivo(args.fichero)
    comprobarBalance(lineas)

# hacer que main() se ejecute solo si se ejecuta el script directamente 
if __name__ == "__main__":
    main()