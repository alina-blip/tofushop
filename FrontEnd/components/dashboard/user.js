$(document).ready(function () {
    $.ajax({
        url: 'http://localhost:8080/user',
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            if (data.length === 0) {
                $('#noUsersMessage').show();
            } else {
                console.log(data);
                data.forEach(function (user) {
                    var userDiv = $('<div class="cart"></div>');
                    userDiv.append('<p>ID: ' + user.id + '</p>');
                    userDiv.append('<p>Rolle: ' + user.role + '</p>');
                    userDiv.append('<p>Nachname: <span class="user-name">' + user.name + '</span></p>');
                    userDiv.append('<p>Vorname: ' + user.surname + '</p>');
                    userDiv.append('<p>Straße: ' + user.street + '</p>');
                    userDiv.append('<p>Hausnummer: ' + user.housenumber + '</p>');
                    userDiv.append('<p>PLZ: ' + user.postalcode + '</p>');
                    userDiv.append('<p>Ort: ' + user.country + '</p>');
                    userDiv.append('<p>E-mail: ' + user.email + '</p>');

                    var changePasswordButton = $('<div class="register-container" id="changePasswordButton"><button>Passwort ändern</button></div>');
                    var deactivateButton = $('<div class="register-container"><button>Deaktivieren</button></div>');
                    var changeRoleButton = $('<div class="register-container"><button>Rolle ändern</button></div>');
                    var editButton = $('<div class="register-container"><button>Benutzer bearbeiten</button></div>');
                    userDiv.append('').append(editButton);
                    userDiv.append('').append(changeRoleButton);
                    userDiv.append('').append(changePasswordButton);
                    userDiv.append('').append(deactivateButton);

                    $('#userList').append(userDiv);


                    editButton.click(function () {
                        // Replace the user information with input fields for editing
                        var nameInput = $('<input type="text" value="' + user.name + '">');
                        var surnameInput = $('<input type="text" value="' + user.surname + '">');
                        var streetInput = $('<input type="text" value="' + user.street + '">');
                        var housenumberInput = $('<input type="text" value="' + user.housenumber + '">');
                        var postalcodeInput = $('<input type="text" value="' + user.postalcode + '">');
                        var countryInput = $('<input type="text" value="' + user.country + '">');
                        var emailInput = $('<input type="text" value="' + user.email + '">');

                        userDiv.empty()
                            .append('<p>ID: ' + user.id + '</p>')
                            .append('<p>Rolle: ' + user.role + '</p>')
                            .append('<p>Nachname: <span class="user-name"></span></p>')
                            .append(nameInput)
                            .append('<p>Vorname: ' + user.surname + '</p>')
                            .append(surnameInput)
                            .append('<p>Straße: ' + user.street + '</p>')
                            .append(streetInput)
                            .append('<p>Hausnummer: ' + user.housenumber + '</p>')
                            .append(housenumberInput)
                            .append('<p>PLZ: ' + user.postalcode + '</p>')
                            .append(postalcodeInput)
                            .append('<p>Ort: ' + user.country + '</p>')
                            .append(countryInput)
                            .append('<p>E-mail: ' + user.email + '</p>')
                            .append(emailInput);

                        var saveButton = $('<button>Speichern</button>');
                        userDiv.append(saveButton);

                        saveButton.click(function () {
                            var newName = nameInput.val();
                            var newSurname = surnameInput.val();
                            var newStreet = streetInput.val();
                            var newHousenumber = housenumberInput.val();
                            var newPostalcode = postalcodeInput.val();
                            var newCountry = countryInput.val();
                            var newEmail = emailInput.val();

                            var newUserData = {
                                id: user.id,
                                role: user.role,
                                name: newName,
                                surname: newSurname,
                                street: newStreet,
                                housenumber: newHousenumber,
                                postalcode: newPostalcode,
                                country: newCountry,
                                email: newEmail,
                                password: user.password,
                            };
                            console.log('New User Data:', newUserData);

                            $.ajax({
                                url: 'http://localhost:8080/user/' + user.id,
                                method: 'PUT',
                                dataType: 'json',
                                contentType: 'application/json',
                                data: JSON.stringify(newUserData),
                                success: function (response) {
                                    console.log('User data updated successfully:', response);
                                    location.reload();
                                },
                                error: function (xhr, status, error) {
                                    console.error('Error updating user data:', xhr, status, error);
                                }
                            });
                        });
                    });



                    changeRoleButton.click(function () {
                        var newRole = prompt("Neue Rolle eingeben (USER oder ADMIN):");
                        if (newRole === "USER" || newRole === "ADMIN") {
                            var newUserData = {
                                id: user.id,
                                password: user.password,
                                role: newRole,
                                name: user.name,
                                surname: user.surname,
                                street: user.street,
                                housenumber: user.housenumber,
                                postalcode: user.postalcode,
                                country: user.country,
                                email: user.email,
                            };

                            $.ajax({
                                url: 'http://localhost:8080/user/' + user.id,
                                method: 'PUT',
                                dataType: 'json',
                                contentType: 'application/json',
                                data: JSON.stringify(newUserData),
                                success: function (response) {
                                    console.log('Rolle wurde erfolgreich aktualisiert:', response);
                                    location.reload();
                                    window.alert('Rolle wurde geändert.');
                                },
                                error: function (xhr, status, error) {
                                    console.error('Fehler beim Aktualisieren der Rolle:', xhr, status, error);
                                }
                            });
                        } else {
                            window.alert("Ungültige Rolle. Erlaubt sind USER oder ADMIN.");
                        }
                    });



                    deactivateButton.click(function () {
                        var newUserData = {
                            id: user.id,
                        };

                        console.log(newUserData);

                        $.ajax({
                            url: 'http://localhost:8080/user/' + user.id,
                            method: 'DELETE',
                            success: function (response) {
                                console.log('User deleted successfully');
                                location.reload();
                            },
                            error: function (xhr, status, error) {
                                console.error('Error deleting user:', xhr, status, error);
                                window.alert("Login as Admin to delete user.")
                            }
                        });
                    });
                    changePasswordButton.click(function () {
                        var passwordInput = $('<input type="password">');
                        var savePasswordButton = $('<div class="register-container"><button>Speichern</button></div>');

                        userDiv.empty()
                            .append('<p>ID: ' + user.id + '</p>')
                            .append('<p>Rolle: ' + user.role + '</p>')
                            .append('<p>Nachname: <span class="user-name">' + user.name + '</span></p>')
                            .append('<p>Vorname: ' + user.surname + '</p>')
                            .append('<p>Straße: ' + user.street + '</p>')
                            .append('<p>Hausnummer: ' + user.housenumber + '</p>')
                            .append('<p>PLZ: ' + user.postalcode + '</p>')
                            .append('<p>Ort: ' + user.country + '</p>')
                            .append('<p>E-mail: ' + user.email + '</p>')
                            .append(passwordInput)
                            .append(savePasswordButton);

                        savePasswordButton.click(function () {
                            var newPassword = passwordInput.val();
                            var newUserData = {
                                id: user.id,
                                password: newPassword,
                                role: user.role,
                                name: user.name,
                                surname: user.surname,
                                street: user.street,
                                housenumber: user.housenumber,
                                postalcode: user.postalcode,
                                country: user.country,
                                email: user.email,
                            };

                            $.ajax({
                                url: 'http://localhost:8080/user/' + user.id,
                                method: 'PUT',
                                dataType: 'json',
                                contentType: 'application/json',
                                data: JSON.stringify(newUserData),
                                success: function (response) {
                                    console.log('Password updated successfully:', response);
                                    location.reload();
                                    window.alert('Passwort wurde geändert.');
                                },
                                error: function (xhr, status, error) {
                                    console.error('Error updating password:', xhr, status, error);
                                }
                            });
                        });
                    });
                });
            }
        },
        error: function (xhr, status, error) {
            console.error(xhr, status, error);
        }
    });
});

