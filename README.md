# schibsted_java_test

Esta aplicación tendrá 3 páginas privadas y un formulario de login.
Para acceder a cualquiera de estas páginas privadas el usuario deberá haber iniciado sesión a traves de la página de login y ademas deberá tener el rol apropiado para poder ver la página.

La app tiene en memoria los siguientes usuarios:
```
Usuario: usuario_pag_1

Pass: pag_1

Acceso: solo a pag_1.jsp

Usuario: usuario_pag_2

Pass: pag_2

Acceso: solo a pag_2.jsp

Usuario: usuario_pag_3

Pass: pag_3

Acceso: solo a pag_3.jsp

Usuario: usuario_pag_12

Pass: pag_12

Acceso: solo a pag_1.jsp y pag_2.jsp
```
La sesión de usuario expirará a los 5 minutos desde la última acción que realizó el usuario

Cada página simplemente deberá mostrar el nombre de la página a la que se esta accediendo y el texto Hola {NOMBRE_DE_USUARIO}. Además tendrá un botón para cerrar la sesión de usuario.

En el caso de intentar acceder a una de estas páginas sin haber iniciado sesión la aplicación redirigirá al usuario automáticamente a la página de login

En el caso de intentar acceder a una de estas páginas con sesión iniciada pero con un usuario que no tenga el rol adecuado para acceder a la página la aplicación devolverá un código de error apropiado indicando que el acceso esta denegado.
