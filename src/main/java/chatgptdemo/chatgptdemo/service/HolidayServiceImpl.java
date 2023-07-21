package chatgptdemo.chatgptdemo.service;

import chatgptdemo.chatgptdemo.entity.HolidayDto;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class HolidayServiceImpl implements HolidayService{
    private static final String CSV_PATH = "classpath:holiday.csv";

    @Override
    public boolean addHoliday(List<HolidayDto> holidays) {
        if (CollectionUtils.isEmpty(holidays)){
            return false;
        }
        List<HolidayDto> holidayInCsv = readCsvFile(CSV_PATH);
        List<HolidayDto> holidayToInsert = new ArrayList<>(Collections.emptyList());
        for (HolidayDto holiday : holidays){
            if (!CollectionUtils.contains(holidayInCsv.iterator(), holiday) && judgeDate(holiday.getHolidayDate())){
                holidayToInsert.add(holiday);
            }
            holidayToInsert.add(holiday);
        }
        if (CollectionUtils.isEmpty(holidayToInsert)){
            return true;
        }
        return writeCsvFile(holidayToInsert, CSV_PATH);
    }

    @Override
    public boolean setHolidays(List<HolidayDto> holidays) {
        //update holidays in csv file   if holiday exists, update it, else add it
        if (CollectionUtils.isEmpty(holidays)){
            return false;
        }
        List<HolidayDto> holidayInCsv = readCsvFile(CSV_PATH);
        List<HolidayDto> holidayToInsert = new ArrayList<>(Collections.emptyList());
        for (HolidayDto holiday : holidays){
            if (CollectionUtils.contains(holidayInCsv.iterator(), holiday) && judgeDate(holiday.getHolidayDate())){
                holidayToInsert.add(holiday);
            }
            holidayToInsert.add(holiday);
        }
        if (CollectionUtils.isEmpty(holidayToInsert)){
            return true;
        }
        return writeCsvFile(holidayToInsert, CSV_PATH);


    }

    @Override
    public boolean deleteHolidays(List<HolidayDto> holidays) {
        //delete holidays in csv file
        if (CollectionUtils.isEmpty(holidays)){
            return true;
        }
        List<HolidayDto> holidayInCsv = readCsvFile(CSV_PATH);
        List<HolidayDto> holidayToInsert = new ArrayList<>(Collections.emptyList());
        for (HolidayDto holiday : holidays){
            if (CollectionUtils.contains(holidayInCsv.iterator(), holiday) && judgeDate(holiday.getHolidayDate())){
                holidayToInsert.add(holiday);
            }
            holidayToInsert.add(holiday);
        }
        if (CollectionUtils.isEmpty(holidayToInsert)){
            return true;
        }
        return false;
    }

    @Override
    public boolean holidayExists(HolidayDto holiday) {
        return false;
    }

    @Override
    public List<HolidayDto> getNextYearHoliday() {
        return null;
    }

    @Override
    public HolidayDto getNextHoliday() {
        return null;
    }

    //add a method to judge holiday if exists
    public boolean judgeHoliday(HolidayDto holiday){
        // jude holiday if exists by countryCode and holidayDate


        return false;
    }

    //add a method to read csv file
    public List<HolidayDto> readCsvFile(String csvPath){
        MultipartFile csvFile = new MockMultipartFile("file", "holiday.csv", "text/plain", "holiday.csv".getBytes());
        //parse csvFile to List<HolidayDto>
        InputStreamReader in = null;
        String s = null;
        List<HolidayDto> result = new ArrayList<>();
        try {
            in = new InputStreamReader(csvFile.getInputStream(), "utf-8");
            BufferedReader bufferedReader = new BufferedReader(in);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                HolidayDto holidayDto = new HolidayDto();
                holidayDto.setCountryCode(splitResult(split[0]));
                holidayDto.setCountryDesc(splitResult(split[1]));
                holidayDto.setHolidayDate(splitResult(split[2]));
                holidayDto.setHolidayDesc(splitResult(split[3]));
                result.add(holidayDto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    private static String splitResult(String once) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < once.length(); i++) {
            if (once.charAt(i) != '"') {
                result.append(once.charAt(i));
            }
        }
        return result.toString();
    }

    private boolean writeCsvFile(List<HolidayDto> holidays, String csvPath){
        if (CollectionUtils.isEmpty(holidays)){
            return false;
        }
        //write csv file
        File outFile = new File(csvPath);
        FileOutputStream out;
        OutputStreamWriter osw;
        try {
            out = new FileOutputStream(outFile);
            osw = new OutputStreamWriter(out, "utf-8");

            // 内容
            for (HolidayDto holidayDto : holidays) {
                osw.write(holidayDto.getCountryCode());
                osw.write(",");
                osw.write(holidayDto.getCountryDesc());
                osw.write(",");
                osw.write(holidayDto.getHolidayDate());
                osw.write(",");
                osw.write(holidayDto.getHolidayDesc() + "\r\n");
            }
            // 写完毕关闭流
            osw.flush();
            out.close();
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //method to transfer a yyyy-mm-dd date String to a date
    public boolean judgeDate(String dateString){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(dateString);
            return true;
        }catch (Exception e){

            e.printStackTrace();
        }
        return false;
    }


}
