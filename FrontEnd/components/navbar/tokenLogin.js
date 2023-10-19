$(document).ready(function () {

 
 
    var localStorageToken = localStorage.getItem("localStorageToken");
  
    

    ////// beim ausloggen wird der Token gelöscht
    $("#logoutButton").click(function () {
      localStorageToken = localStorage.removeItem("localStorageToken");
      console.log("logout hat funktioniert, Token: " + localStorageToken);
      $("#loginForm").show();
      $("#loginFo").show();
      $("#logoutBut").hide();
      $("#dashboard").hide();
      $("#dashboardPhone").hide();
      $(this).hide();
      location.reload();
    });


    /////// die benachrichtigung das der token noch da ist nachdem die Seite neu geladen wurde
    if (localStorageToken) {
      $("#loginForm").hide();
      $("#logoutButton").show();
      $("#loginFo").hide();
      $("#logoutBut").show();
      
      // Die Anfrage an den Server für andere Operationen (z. B. API-Aufrufe) enthält den Token im "Authorization"-Header
      $.ajax({
        type: "GET",
        url: "http://localhost:8080/secured",
        headers: {
          Authorization: "Bearer " + localStorageToken, // Hinzufügen des Tokens zum Header
        },
        success: function (response) {
          // Verarbeite die Antwort vom geschützten Endpunkt
          console.log(
            "Erfolgreich angemeldet nachdem die seite refresht wurde " +
              localStorageToken
              
          );

           /////Der Token besteht normalerweise aus einem Header, Payload und einer Signatur, die durch Punkte voneinander getrennt sind.//////
      //// [1] gibt den zweiten Teil an, der das Payload enthält.////////
      // Den Token decodieren, um die Informationen zu erhalten///////
      
          var tokenData = JSON.parse(atob(localStorageToken.split(".")[1]));
          var role = tokenData.a;
          console.log("Benutzer-Rolle " + role);
          if(role == "ADMIN"){
          $("#dashboard").show();
          $("#dashboardPhone").show();
          
          }
          else{
            $("#dashboard").hide();
            $("#dashboardPhone").show();
          }
        },
        error: function (error) {
          // Fehlerbehandlung
          console.log("Anmeldung hat nicht funktioniert");
        },
      });
      
    }
    $("#loginForm").submit(function (event) {
      event.preventDefault();

      var email = $("#emailLogin").val();
      var password = $("#passwordLogin").val();
      var loginData = {
        email: email,
        password: password,
      };

      $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/auth/login",
        data: JSON.stringify(loginData),
        contentType: "application/json",
        success: function (response) {
          // Hier wird der Token aus der Serverantwort extrahiert
          var token = response.accessToken;

          // Die Anfrage an den Server für andere Operationen (z. B. API-Aufrufe) enthält den Token im "Authorization"-Header
          $.ajax({
            type: "GET",
            url: "http://localhost:8080/secured",
            headers: {
              Authorization: "Bearer " + token, // Hinzufügen des Tokens zum Header
            },
            success: function (response) {
              // Verarbeite die Antwort vom geschützten Endpunkt
              console.log("erfolgreich angemeldet  " + token);
              localStorage.setItem("localStorageToken", token);
              window.location.href = "Warenkorb.html";
            },
            error: function (error) {
              // Fehlerbehandlung
              console.log("anmeldung hat nicht funktioniert");
            },
          });
        },
        error: function (error) {
          // Fehlerbehandlung
          console.log(
            "beim extrahieren des Tokens hat etwas nicht funktioniert"
            
          );
          $("#logoutButton").hide();
          $("#logoutBut").hide();
          $("#loginFo").show();
          $("#loginForm").show();
        },
      });
      
      $("#loginForm").hide();
      $("#logoutButton").show();
      $("#loginFo").hide();
      $("#logoutBut").show();
    });
  });