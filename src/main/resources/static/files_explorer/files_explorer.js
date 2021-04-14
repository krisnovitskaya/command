angular.module('app').controller('filesExplorerController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8989/errands';

    $scope.createErrand = function () {
        $http.post(contextPath + '/api/v1/errands/createErrand', $scope.NewErrandDto)
                .then(function (response) {
                    $scope.errand = null;
                    $scope.errandDetails = null;
                    $scope.NewErrandDto = null;
                    alert('Ваша заявка успешно оформлена');
                });
    };

    $scope.fillFilesList = function (errandsIdsList) {
        $http({
            url: contextPath + '/api/v1/files/listfiles',
            method: 'POST',
            headers: {'Content-type': 'application/json'},
            data: errandsIdsList
        }).then(function (response) {
            console.log("Получение списка загруженных в систему файлов.");
            $scope.filesDtosList = response.data;
        });
    };

    $scope.fillFilesList([]);
});

function uploadFiles(input) {
    var files = input.files;
    for (var i = 0; i < files.length; i++) {
        uploadFile(files[i]);
    }
};

function uploadFile(file) {
    var fileReader = new FileReader();
    fileReader.onload = function () {
        var fileData = fileReader.result;
        sendRequest(fillRequest(file.name, fileData));
    };
    fileReader.readAsDataURL(file);
};

function fillRequest(fileName, fileData) {
    let data = [{
        'id': '',
        'fileName': fileName,
        'fileData': fileData,
        'authorId': 2,
        'errandId': 2,
        'deleted': false
    }];
    return data;
};

function sendRequest(requestData) {
    fetch('./api/v1/files/uploads', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(requestData)
    })
            .then(response => response.json())
            .then(result => {
                console.log('Success:', result);
            })
            .catch(error => {
                console.error('Error:', error);
            });
};

function download(id) {
    console.log("ID - " + id);
    fetch('./api/v1/files/download', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(id)
    })
            .then(response => response.json())
            .then(result => {
                console.log('Success:', result);
            })
            .catch(error => {
                console.error('Error:', error);
            });    
}

function deletes(idsList) {
    console.log("ID LIST - " + idsList);
    fetch('./api/v1/files/deletes', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(id)
    })
            .then(response => response.json())
            .then(result => {
                console.log('Success:', result);
            })
            .catch(error => {
                console.error('Error:', error);
            });    
}