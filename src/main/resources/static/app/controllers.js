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
      $scope.displayedVacations = $scope.vacations;
    });

    $scope.empNumber = "";
    $scope.dateStart = "";
    $scope.dateFinish = "";
    $scope.vacationSortParam = "";

    $scope.vacationsFilter = function () {
      $scope.displayedVacations = $scope.vacations.slice();
      if($scope.helpForUpdate($scope.empNumber)) {
        var empNumberVacations = [];
        for(var i = 0; i < $scope.displayedVacations.length; i++) {
          if($scope.displayedVacations[i].employee.employeeNumber === $scope.empNumber) {
            empNumberVacations.push($scope.displayedVacations[i]);
          }
        }
        $scope.displayedVacations = empNumberVacations;
      }
      if($scope.helpForUpdate($scope.dateStart)) {
        var dateStartVacations = [];
        for(var ii = 0; ii < $scope.displayedVacations.length; ii++) {
          if(new Date($scope.displayedVacations[ii].startVacation) >= $scope.dateStart) {
            dateStartVacations.push($scope.displayedVacations[ii]);
          }
        }
        $scope.displayedVacations = dateStartVacations;
      }
      if($scope.helpForUpdate($scope.dateFinish)) {
        var dateFinishVacations = [];
        for(var iii = 0; iii < $scope.displayedVacations.length; iii++) {
          if(new Date($scope.displayedVacations[iii].finishVacation) <= $scope.dateFinish) {
            dateFinishVacations.push($scope.displayedVacations[iii]);
          }
        }
        $scope.displayedVacations = dateFinishVacations;
      }
      $scope.vacationSort();
    };

    $scope.vacationSort = function() {
      if($scope.vacationSortParam === '1') { //по уменьшению id
        $scope.sortById(true);
      }
      if($scope.vacationSortParam === '2') { //по увеличению id
        $scope.sortById(false);
      }
      if($scope.vacationSortParam === '3') { //по уменьшению даты
        $scope.sortByDate(false);
      }
      if($scope.vacationSortParam === '4') { //по увеличению даты
        $scope.sortByDate(true);
      }
    }

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
        $scope.vacationsFilter();
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
        $scope.vacationsFilter();
      });
      $scope.clearModels();
    };

    $scope.deleteVacation = function(vacation) {
      vacation.$remove(function() {
        $scope.vacations.splice($scope.vacations.indexOf(vacation), 1);
        $scope.vacationsFilter();
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

    $scope.sortById = function (reverse) {
      var originalArray = $scope.displayedVacations.slice();
      var numberOfIterations = $scope.displayedVacations.length;
      $scope.displayedVacations = [];
      var indexInArray, minNumber;
      for(var i = 0; i < numberOfIterations; i++) {
        for(var ii = 0; ii < originalArray.length; ii++) {
          if(ii === 0) {
            minNumber = originalArray[ii].employee.employeeNumber;
            indexInArray = ii;
          }
          else {
            if(originalArray[ii].employee.employeeNumber < minNumber) {
              minNumber = originalArray[ii].employee.employeeNumber;
              indexInArray = ii;
            }
          }
        }
        $scope.displayedVacations.push(originalArray[indexInArray]);
        originalArray.splice(indexInArray, 1);
      }
      if(reverse === true) {
        $scope.displayedVacations.reverse();
      }
    }

    $scope.sortByDate = function (reverse) {
      var originalArray = $scope.displayedVacations.slice();
      var numberOfIterations = $scope.displayedVacations.length;
      $scope.displayedVacations = [];
      var indexInArray, maxDate;
      for(var i = 0; i < numberOfIterations; i++) {
        for(var ii = 0; ii < originalArray.length; ii++) {
          if(ii === 0) {
            maxDate = new Date(originalArray[ii].finishVacation);
            indexInArray = ii;
          }
          else {
            if(new Date(originalArray[ii].finishVacation) > maxDate) {
              maxDate = new Date(originalArray[ii].finishVacation);
              indexInArray = ii;
            }
          }
        }
        $scope.displayedVacations.push(originalArray[indexInArray]);
        originalArray.splice(indexInArray, 1);
      }
      if(reverse === true) {
        $scope.displayedVacations.reverse();
      }
    }

  };

  VacationController.$inject = ['$scope', '$rootScope', 'Vacation'];
  angular.module("test-data-drivers.controllers").controller("VacationController", VacationController);
}(angular));