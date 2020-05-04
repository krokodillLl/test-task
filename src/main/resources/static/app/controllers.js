(function(angular) {
  var EmployeeController = function($scope, $rootScope, Employee) {
    Employee.query(function(response) {
      $scope.employees = response ? response : [];
      $rootScope.employees = $scope.employees;
    });

    $scope.selectEmployee = function(employee) {
      employee.$getOne(function(employee) {
        $scope.selectedEmployee = employee;
      });
    };


    $scope.addEmployee = function(employeeNumber, name, surname, patronymic, position, login, password, birthday, startWork) {
      for(var i = 0; i < $scope.employees.length; i++) {
        if($scope.employees[i].employeeNumber === employeeNumber) {
          $scope.invalidEmployeeNumber = "Введенный Вами id уже используется. Пожалуйста, проверьте введенные данные";
          return;
        }
      }
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
        $rootScope.employees = $scope.employees;
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
        $rootScope.employees = $scope.employees;
        $scope.selectedEmployee = employee;
      });
      $scope.clearModels();
    };
    
    $scope.deleteEmployee = function(employee) {
      employee.$remove(function() {
        $scope.employees.splice($scope.employees.indexOf(employee), 1);
        $rootScope.employees = $scope.employees;
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
      $scope.invalidEmployeeNumber = "";
    };

    $scope.helpForUpdate = function (param) {
    return !(typeof param === "undefined" || param === null || param ===  "")
    };
  };

  EmployeeController.$inject = ['$scope', '$rootScope', 'Employee'];
  angular.module("test-data-drivers.controllers").controller("EmployeeController", EmployeeController);

  var VacationController = function($scope, $rootScope, Vacation) {
    Vacation.query(function(response) {
      $scope.vacations = response ? response : [];
    });

    $scope.selectVacation = function(vacation) {
      $scope.selectedVacation = vacation;
    };


    $scope.addVacation = function(employeeNumber, startVacation, finishVacation) {
      var employee;
      for(var i = 0; i < $rootScope.employees.length; i++) {
        if($rootScope.employees[i].employeeNumber === employeeNumber) {
          employee = $rootScope.employees[i];
          break;
        }
      }
      if(!$scope.helpForUpdate(employee)) {
        $scope.invalidEmployeeNumber = "Введенный Вами id сотрудника еще не зарегистрирован. Пожалуйста, проверьте введенные данные";
        return;
      }
      new Vacation({
        employeeNumber: employeeNumber,
        startVacation: startVacation,
        finishVacation: finishVacation,
        employee: employee
      }).$add(function(vacation) {
        $scope.vacations.push(vacation);
      });
      $scope.clearModels();
    };

    $scope.updateVacation = function(startVacation, finishVacation, vacation) {
      for(var i = 0; i < $rootScope.employees.length; i++) {
        if($rootScope.employees[i].employeeNumber === vacation.employee.employeeNumber) {
          var employee = $rootScope.employees[i];
          break;
        }
      }
      $scope.vacations.splice($scope.vacations.indexOf(vacation), 1);
      new Vacation({
        id: vacation.id,
        employeeNumber: vacation.employee.employeeNumber,
        startVacation: $scope.helpForUpdate(startVacation) ? startVacation : vacation.startVacation,
        finishVacation: $scope.helpForUpdate(finishVacation) ? finishVacation : vacation.finishVacation,
        employee: employee
      }).$update(function (vacation) {
        $scope.vacations.push(vacation);
        $scope.selectedVacation = vacation;
      });
      $scope.clearModels();
    };

    $scope.deleteVacation = function(vacation) {
      vacation.$remove(function() {
        $scope.vacations.splice($scope.vacations.indexOf(vacation), 1);
      });
    };

    $scope.clearModels = function() {
      $scope.startVacation = "";
      $scope.finishVacation = "";
      $scope.employeeNumber = "";
      $scope.invalidEmployeeNumber = "";
    };

    $scope.helpForUpdate = function (param) {
      return !(typeof param === "undefined" || param === null || param ===  "")
    };
  };

  VacationController.$inject = ['$scope', '$rootScope', 'Vacation'];
  angular.module("test-data-drivers.controllers").controller("VacationController", VacationController);
}(angular));