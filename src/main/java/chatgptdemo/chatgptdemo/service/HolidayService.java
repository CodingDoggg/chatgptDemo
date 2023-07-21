package chatgptdemo.chatgptdemo.service;

import chatgptdemo.chatgptdemo.entity.HolidayDto;

import java.util.List;

public interface HolidayService {

    boolean addHoliday(List<HolidayDto> holidays);

    boolean setHolidays(List<HolidayDto> holidays);

    boolean deleteHolidays(List<HolidayDto> holidays);

    boolean holidayExists(HolidayDto holiday);

    List<HolidayDto> getNextYearHoliday();

    HolidayDto getNextHoliday();




}
