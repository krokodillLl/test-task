package com.netrika.ru.test.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.netrika.ru.test.dto.VacationForEmployeeTO;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

public class VacationForEmployeeTOConverter implements JsonSerializer<VacationForEmployeeTO> {
    @Override
    public JsonElement serialize(VacationForEmployeeTO vacationForEmployeeTO, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("Дата начала отпуска", new SimpleDateFormat("yyyy-MM-dd").format(vacationForEmployeeTO.getStartVacation()));
        object.addProperty("Дата окончания отпуска", new SimpleDateFormat("yyyy-MM-dd").format(vacationForEmployeeTO.getFinishVacation()));
        object.addProperty("Количество использованных дней отпуска", "" +
                (vacationForEmployeeTO.getFinishVacation().getTime() - vacationForEmployeeTO.getStartVacation().getTime()) / (1000 * 60 * 60 * 24));
        return object;
    }
}
