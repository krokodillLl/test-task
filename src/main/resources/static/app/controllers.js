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
      $scope.clearModels();
    };
    
    $scope.updateEmployee = function(employeeNumber, name, surname, patronymic, position, login, password, birthday, startWork, employee) {
      $scope.employees.splice($scope.employees.indexOf(employee), 1);
      new Employee({
        id: employee.id,
        employeeNumber: $scope.helpForUpdate(employeeNumber) ? employeeNumber : employee.employeeNumber,
        name:  $scope.helpForUpdate(name) ? name : employee.name,
        surname:  $scope.helpForUpdate(surname) ? surname : employee.surname,
        patronymic:  $scope.helpForUpdate(patronymic) ? patronymic : employee.patronymic,
        position:  $scope.helpForUpdate(position) ? position : employee.position,
        login: login,
        password: password,
        birthday:  $scope.helpForUpdate(birthday) ? birthday : employee.birthday,
        startWork:  $scope.helpForUpdate(startWork) ? startWork : employee.startWork,
        vacations: employee.vacations
      }).$update(function (employee) {
        $scope.employees.push(employee);
        $scope.selectedEmployee = employee;
      });
      $scope.clearModels();
    };
    
    $scope.deleteEmployee = function(employee) {
      employee.$remove(function() {
        $scope.employees.splice($scope.employees.indexOf(employee), 1);
      });
    };

    $scope.clearModels = function() {
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

    $scope.helpForUpdate = function (param) {
    return !(typeof param === "undefined" || param === null || param ===  "")
    };
  };

  EmployeeController.$inject = ['$scope', 'Employee'];
  angular.module("test-data-drivers.controllers").controller("EmployeeController", EmployeeController);
}(angular));