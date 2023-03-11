angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $rootScope, $http, $localStorage) {

    if ($localStorage.springWebUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
    }

    const contextPath = 'http://localhost:8080/app/api/v1';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
        });
    };


    $scope.addProduct = function (product) {
        $http.post(contextPath + '/products', product).success(function () {
            $scope.product.title = '';
            $scope.product.cost = '';
        }).then(function () {
            $scope.loadProducts();
        })
    };

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8080/app/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                    $scope.loadProducts();
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

    $scope.loadCart = function () {
        $http.get(contextPath + '/cart').then(function (response) {
            $scope.cart = response.data;
        })
    };

    $scope.CartProductAdd = function (productId) {
        $http.post(contextPath + '/cart/' + productId).then(function () {
            $scope.loadCart();
        })
    };

    $scope.ProductDelete = function (productId) {
        $http.delete(contextPath + "/products/" + productId).then(function () {
            $scope.loadProducts();
        })
    };

    $scope.ChangeQuantityPlus = function (productId) {
        $http.get(contextPath + '/cart/changePlus/' + productId).then(function () {
            $scope.loadCart();
        })
    };

    $scope.ChangeQuantityMinus = function (productId) {
        $http.get(contextPath + '/cart/changeMinus/' + productId).then(function () {
            $scope.loadCart();
        })
    };

    $scope.deleteItemCart = function (productId) {
        $http.delete(contextPath + '/cart/' + productId).then(function () {
            $scope.loadCart();
        })
    };


    $scope.clearCart = function (productId) {
        $http.delete(contextPath + '/cart/clearCart').then(function () {
            $scope.loadCart();
        })
    };

    $scope.createdOrder = function () {
        $http.post(contextPath + '/order') .then(function () {
            $scope.clearCart();
        })
    };

    // $scope.createdOrder = function (address, email) {
    //     $http.post(contextPath + '/order'+ address + '/' + email) .then(function () {
    //         $scope.clearCart();
    //     })
    // };

    $scope.loadProducts();
    $scope.loadCart();

});