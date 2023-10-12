$(document).ready(function () {
    $.ajax({
        url: 'http://localhost:8080/user',
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            if (data.length === 0) {
                $('#noUsersMessage').show();
            } else {
                data.forEach(function (user) {
                    var userDiv = $('<div class="cart"></div>');
                    userDiv.append('<p>ID: ' + user.id + '</p>');
                    userDiv.append('<p>Nachname: <span class="user-name">' + user.name + '</span></p>');
                    userDiv.append('<p>Vorname: ' + user.surname + '</p>');
                    userDiv.append('<p>Straße: ' + user.street + '</p>');
                    userDiv.append('<p>Hausnummer: ' + user.housenumber + '</p>');
                    userDiv.append('<p>PLZ: ' + user.postalcode + '</p>');
                    userDiv.append('<p>Ort: ' + user.country + '</p>');
                    userDiv.append('<p>E-mail: ' + user.email + '</p>');

                    var changeNameButton = $('<div class="register-container" id="changeNameButton"><button>Name ändern</button></div>');
                    var changePasswordButton = $('<div class="register-container" id="changePasswordButton"><button>Passwort ändern</button></div>');
                    var deactivateButton = $('<div class="register-container"><button>Deaktivieren</button></div>');

                    userDiv.append('').append(changeNameButton);
                    userDiv.append('').append(changePasswordButton);
                    userDiv.append('').append(deactivateButton);

                    $('#userList').append(userDiv);

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
                            }
                        });
                    });
                    changePasswordButton.click(function () {
                        var passwordInput = $('<input type="password">');
                        var savePasswordButton = $('<div class="register-container"><button>Speichern</button></div>');

                        userDiv.empty()
                            .append('<p>ID: ' + user.id + '</p>')
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
                    changeNameButton.click(function () {
                        var nameInput = $('<input type="text">');
                        var saveButton = $('<button>Speichern</button>');
                        userDiv.find('.user-name').replaceWith(nameInput);
                        userDiv.find('#changeNameButton').empty().append(saveButton);

                        saveButton.click(function () {
                            var newName = nameInput.val();
                            var newUserData = {
                                id: user.id,
                                name: newName,
                                surname: user.surname,
                                street: user.street,
                                housenumber: user.housenumber,
                                postalcode: user.postalcode,
                                country: user.country,
                                email: user.email,
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
                });
            }
        },
        error: function (xhr, status, error) {
            console.error(xhr, status, error);
        }
    });
});

