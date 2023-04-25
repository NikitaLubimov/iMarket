angular.module('iMarket').controller('cartController', function ($scope, $http, $location, $localStorage) {

    const contextPathCoreService = 'http://localhost:5555/core/api/v1';
    const contextPathCartsService = 'http://localhost:5555/cart/api/v1';

    $scope.loadCart = function () {
        $http.get(contextPathCartsService + '/cart/' + $localStorage.iMarketGuestCartId).then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.clearCart = function () {
        $http.delete(contextPathCartsService + '/cart/' + $localStorage.iMarketGuestCartId + '/clearCart').then(function (response) {
            //http://localhost:5555/cart/api/v1/cart/clearCart7e816d57-f0b0-4d81-9256-59051aa3905a/clear
            //http://localhost:5555/cart/api/v1/cart/7e816d57-f0b0-4d81-9256-59051aa3905a/clearCart
            $scope.loadCart();
        });
    }

    $scope.removeFromCart = function (productId) {
        $http.delete(contextPathCartsService + '/cart/' + $localStorage.iMarketGuestCartId + '/remove/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.createdOrder = function () {
        $http.post(contextPathCoreService + '/order').then(function (response) {
            alert('Заказ оформлен');
            $scope.clearCart();
            $scope.loadCart();
        });
    }

    $scope.deleteItemCart = function (productId) {
        $http.delete(contextPathCartsService + '/cart/' + $localStorage.iMarketGuestCartId + '/' + productId).then(function () {
            //http://localhost:5555/cart/api/v1/cart/1
            $scope.loadCart();
        })
    };

    $scope.ChangeQuantityPlus = function (productId) {
        $http.get(contextPathCartsService + '/cart/' + $localStorage.iMarketGuestCartId + '/changePlus/' + productId).then(function () {
            //http://localhost:5555/cart/api/v1/cart/changePlus/3
            $scope.loadCart();
        })
    };

    $scope.ChangeQuantityMinus = function (productId) {
        $http.get(contextPathCartsService + '/cart/' + $localStorage.iMarketGuestCartId + '/changeMinus/' + productId).then(function () {
            $scope.loadCart();
        })
    };

    $scope.loadCart();
});