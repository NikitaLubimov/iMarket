angular.module('app', []).controller('indexController', function ($scope, $http) {
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
           $scope.product.id = '';
           $scope.product.title = '';
           $scope.product.cost = '';
        }).then(function (){
            $scope.loadProducts();
        })
    };

    $scope.shoppingListUserByProduct = function () {
        $http.get(contextPath + '/products/shoppingList')
            .then(function (response) {
                $scope.shoppingList = response.data;
            });
    };

    $scope.loadProducts();

});