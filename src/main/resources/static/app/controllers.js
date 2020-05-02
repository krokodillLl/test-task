(function(angular) {
  var EmployeeController = function($scope, Employee) {
    Employee.query(function(response) {
      $scope.employees = response ? response : [];
    });

    $scope.selectEmployee = function(employee) {
      $scope.selectedEmployee = employee;
    };


    $scope.addEmployee = function(employeeNumber, name, surname, patronymic, position, login, password, birthday, startWork) {
      new Employee({
        employeeNumber: employeeNumber,
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
      $scope.employeeNumber = "";
          $scope.name = "";
          $scope.surname = "";
          $scope.patronymic = "";
          $scope.position = "";
          $scope.login = "";
          $scope.password = "";
          $scope.birthday = "";
          $scope.startWork = "";
    };
    
    $scope.updateEmployee = function(employeeNumber, name, surname, patronymic, position, login, password, birthday, startWork, employee) {
      $scope.employees.splice($scope.employees.indexOf(employee), 1);
      new Employee({
        id: employee.id,
        employeeNumber: employeeNumber != null ? employeeNumber : employee.employeeNumber,
        name: name != null ? name : employee.name,
        surname: surname != null ? surname : employee.surname,
        patronymic: patronymic != null ? patronymic : employee.patronymic,
        position: position != null ? position : employee.position,
        login: login != null ? login : employee.login,
        password: password != null ? password : employee.password,
        birthday: birthday != null ? birthday : employee.birthday,
        startWork: startWork != null ? startWork : employee.startWork
      }).$update(function (employee) {
        $scope.employees.push(employee);
      });
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