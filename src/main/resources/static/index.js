angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $rootScope, $http, $localStorage) {

    if ($localStorage.springWebUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
    }

    const contextPath = 'http://localhost:8080/app';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products/all')
            .then(function (response) {
                $scope.productsList = response.data;
            });
    };

    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + '/products/delete/' + productId)
            .then(function () {
                $scope.loadProducts();
            });
    };

    $scope.addProduct = function (product) {
        $http.post(contextPath + '/products/add', product).success(function () {
            $scope.product.title = '';
            $scope.product.cost = '';
        }).then(function () {
            $scope.loadProducts();
        })
    };

    $scope.costBetween = function () {
        $http({
            uri: contextPath + '/products/costBetween',
            method: 'get',
            params: {
                min: $scope.costBetweenAdd.min,
                max: $scope.costBetweenAdd.max
            }
        }).then(function (response) {
            $scope.productsList = response.data;
        })
    };

    // $scope.shoppingListUserByProduct = function (productId) {
    //     $http.get(contextPath + '/products/shoppingList/' + productId)
    //         .then(function (response) {
    //             $scope.shoppingList = response.data;
    //             $location.path('shoppingList.html')
    //         });
    // };

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8189/app/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {

            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.springWebUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.loadProducts();

});