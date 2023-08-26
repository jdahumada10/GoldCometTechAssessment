package com.juan.ahumada.techassessment.services;

import com.juan.ahumada.techassessment.exceptions.IOCustomException;
import java.io.File;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface IIOService {
	String streamTextFile(MultipartFile file) throws IOCustomException;

	String readBinaryFile(MultipartFile file) throws IOCustomException;

	byte[] convertTextToBinaryAndDownload(String text) throws IOCustomException;

	<T> List<T> readJsonCsvAsPojo(MultipartFile file, Class<T> clazz) throws IOCustomException;
}
