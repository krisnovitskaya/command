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
function uploadFiles(files) {
    var reader = new FileReader();
    reader.readAsArrayBuffer(files[0]);
    reader.onloadend = function (evt) {
        var fileByteArray = [];
        if (evt.target.readyState == FileReader.DONE) {
            var arrayBuffer = evt.target.result,
                    array = new Uint8Array(arrayBuffer);
            for (var i = 0; i < array.length; i++) {
                fileByteArray.push(array[i]);
            }
        }
        return fileByteArray;
    };
    let data = [{
            'id': '',
            'fileName': files[0].name,
            'fileData': fileByteArray,
            'authorId': 2,
            'errandId': 2,
            'deleted': false
        }];
    fetch('./api/v1/files/upload', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(data)
    })
            .then(response => response.json())
            .then(result => {
                console.log('Success:', result);
            })
            .catch(error => {
                console.error('Error:', error);
            });
};