package com.juan.ahumada.techassessment.mappers;

import com.juan.ahumada.techassessment.dtos.EmployeeDto;
import com.juan.ahumada.techassessment.models.Employee;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EmployeeMapper {
	EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

	EmployeeDto toDto(final Employee employee);

	Employee toEntity(final EmployeeDto employeeDto);
}
