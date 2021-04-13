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
        loadFile(files[i]);
    }
};

function loadFile(file) {
    var fileReader = new FileReader();
    fileReader.onloadend = function () {
        var fileData = fileReader.result;
        var array = new Uint8Array(fileData);
        var fileByteArray = new Array();
        for (var i = 0; i < array.length; i++) {
            fileByteArray.push(array[i]);
        }
        sendRequest(fillRequest(file.name, fileByteArray));
    };
    fileReader.readAsArrayBuffer(file);
};

function fillRequest(fileName, fileByteArray) {
    let data = [{
        'id': '',
        'fileName': fileName,
        'fileData': fileByteArray,
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

function downloadFiles(idsList) {
    fetch('./api/v1/files/download', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(idsList)
    })
            .then(response => response.json())
            .then(result => {
                console.log('Success:', result);
            })
            .catch(error => {
                console.error('Error:', error);
            });    
}