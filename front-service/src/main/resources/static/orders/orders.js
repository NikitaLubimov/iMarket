angular.module('iMarket').controller('ordersController', function ($scope, $http) {

    const contextPathCoreService = 'http://localhost:5555/core/api/v1';
    const contextPathCartsService = 'http://localhost:5555/cart/api/v1';

    $scope.loadOrders = function () {
        $http.get(contextPathCoreService + '/order').then(function (response) {
            $scope.orders = response.data;
        });
    }

    $scope.loadOrders();
});