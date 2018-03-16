# poeBrowser
Es una aplicacion para android que recibe los datos del ranking del videojuego Path of Exile https://www.pathofexile.com/
# API
La API que utiliza es http://api.pathofexile.com/ladders/Standard?offset=x&limit=y donde offset seria el rango a partir 
del que buscar y limit la cantidad de resultados que recibes.
# Aplicacion
La aplicacion muestra una lista de personajes ordenadas por su ranking.

Tiene un campo de busqueda en el cual puedes introducir la posicion que buscara, si introduces algo que no sea un numero o un numero menor que 1, buscara a partir de la posicion 1.

Si pulsas sobre un personaje te muestra una vista con toda su informacion y para volver a la lista
basta con deslizar el dedo de izquierda a derecha,

Para volver con el gesto swipe he utilizado esta libreria: https://github.com/bluzwong/swipeback
