(function(angular) {
  var EmployeeController = function($scope, Employee) {
    Employee.query(function(response) {
      $scope.employees = response ? response : [];
    });
    
    $scope.addEmployee = function(id, name, surname, patronymic, position, login, password, birthday, startWork) {
      new Employee({
        id: id,
        name: name,
        surname: surname,
        patronymic: patronymic,
        position: position,
        login: login,
        password: password,
        birthday: birthday,
        startWork: startWork
      }).$add(function(employee) {
        $scope.employees.push(employee);
      });
      $scope.id = "";
    };
    
    $scope.updateEmployee = function(employee) {
      employee.$update();
    };
    
    $scope.deleteEmployee = function(employee) {
      employee.$remove(function() {
        $scope.employees.splice($scope.employees.indexOf(employee), 1);
      });
    };
  };

  EmployeeController.$inject = ['$scope', 'Employee'];
  angular.module("test-data-drivers.controllers").controller("EmployeeController", EmployeeController);
}(angular));