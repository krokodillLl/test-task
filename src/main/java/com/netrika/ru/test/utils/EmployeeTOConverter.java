package com.netrika.ru.test.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.netrika.ru.test.dto.EmployeeTO;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class EmployeeTOConverter implements JsonSerializer<EmployeeTO> {
    @Override
    public JsonElement serialize(EmployeeTO employeeTO, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("ФИО", employeeTO.getSurname() + " " + employeeTO.getName() + " " + employeeTO.getPatronymic());
        object.addProperty("id сотрудника", employeeTO.getEmployeeNumber().toString());
        object.addProperty("Должность", employeeTO.getPosition());
        object.addProperty("Дата начала работы", new SimpleDateFormat("yyyy-MM-dd").format(employeeTO.getStartWork()));
        if(!Objects.equals(employeeTO.getJsonVacations(), "[]")) {
            object.addProperty("Список отпусков", employeeTO.getJsonVacations());
        }
        return object;
    }
}
