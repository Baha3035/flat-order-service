package kg.megacom.FlatOrdering.HouseFlatApp.services.impl;

import kg.megacom.FlatOrdering.HouseFlatApp.dao.ReserveHistoryRepo;
import kg.megacom.FlatOrdering.HouseFlatApp.enums.ReserveStatus;
import kg.megacom.FlatOrdering.HouseFlatApp.mappers.ReserveHistoryMapper;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.HouseDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.PayHistoryDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.ReserveHistoryDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.ReserveHistory;
import kg.megacom.FlatOrdering.HouseFlatApp.models.inputs.InputReserveHistoryData;
import kg.megacom.FlatOrdering.HouseFlatApp.models.outputs.OutputReserveData;
import kg.megacom.FlatOrdering.HouseFlatApp.services.HouseService;
import kg.megacom.FlatOrdering.HouseFlatApp.services.PayHistoryService;
import kg.megacom.FlatOrdering.HouseFlatApp.services.ReserveHistoryService;
import kg.megacom.FlatOrdering.HouseFlatApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class ReserveHistoryServiceImpl implements ReserveHistoryService {

    @Autowired
    private ReserveHistoryRepo reserveHistoryRepo;
    @Autowired
    private HouseService houseService;
    @Autowired
    private UserService userService;
    @Autowired
    private PayHistoryService payHistoryService;


    @Override
    public ReserveHistoryDto saveWithInput(InputReserveHistoryData inputReserveHistoryData) {
        List<ReserveHistoryDto> allReserves = findAll();
        LocalDate startDate = inputReserveHistoryData.getStartDate();
        LocalDate endDate = inputReserveHistoryData.getEndDate();
        if(checkFreeTime(allReserves, startDate, endDate)){
            throw new RuntimeException("House is reserved in this range of days!!!");
        }
        Long range = ChronoUnit.DAYS.between(inputReserveHistoryData.getStartDate(), inputReserveHistoryData.getEndDate());
        HouseDto houseDto = houseService.findById(inputReserveHistoryData.getHouseId());
        double priceOfFlat = houseDto.getPrice();

        ReserveHistoryDto reserveHistoryDto = new ReserveHistoryDto();
        reserveHistoryDto.setStartDate(startDate);
        reserveHistoryDto.setEndDate(endDate);
        reserveHistoryDto.setHouse(houseDto);
        reserveHistoryDto.setUser(userService.findById(inputReserveHistoryData.getCustomerUser()));
        reserveHistoryDto.setReserveStatus(ReserveStatus.RESERVED);
        reserveHistoryDto.setTotalPrice(countPrice(range.intValue(), priceOfFlat));
        reserveHistoryDto = save(reserveHistoryDto); //saving reserveHistory

      return reserveHistoryDto;
    }



    private double countPrice(int days, double price){
        return days * price;
    }

    private boolean checkFreeTime(List<ReserveHistoryDto> allReserves, LocalDate startTime, LocalDate endTime) {
        return allReserves.stream().anyMatch(x->
                (startTime.isEqual(endTime))
                        ||
                        (x.getStartDate().isEqual(startTime) || x.getEndDate().isEqual(endTime))
                        ||
                        (x.getStartDate().isBefore(startTime) && x.getEndDate().isAfter(endTime))
                        ||
                        (x.getStartDate().isAfter(startTime) && x.getEndDate().isBefore(endTime))
        );
    }

    @Override
    public OutputReserveData pay(Long reserve_id, double cash) {
        if (cash <= 0) {
            throw new RuntimeException("You gotta pay!");
        }
        ReserveHistoryDto reserveHistoryDto = findById(reserve_id);
        ReserveStatus reserveStatus = reserveHistoryDto.getReserveStatus();
        ReserveHistoryMapper reserveHistoryMapper = new ReserveHistoryMapper();
        if (reserveStatus.equals(ReserveStatus.RESERVED)) {
            PayHistoryDto payHistoryDto = new PayHistoryDto();
            Date todayDateToConvert = new Date();
            LocalDate todaysDate = todayDateToConvert.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            LocalDate addDateOfReserve = reserveHistoryDto.getAddDate();
            long range = ChronoUnit.DAYS.between(todaysDate, addDateOfReserve);
            if (range >= 1) {
                reserveHistoryDto.setReserveStatus(ReserveStatus.CANCELLED);
                save(reserveHistoryDto);
                throw new RuntimeException("You're out of time!!!");
            }

            double totalPrice = reserveHistoryDto.getTotalPrice();
            List<PayHistoryDto> payHistoryDtos = payHistoryService.findByReserveHistoryId(reserve_id);

            if (payHistoryDtos == null) {
                payHistoryDto.setCash(cash);

            } else {
                double moneyPaidBefore = payHistoryDtos.stream().mapToDouble(PayHistoryDto::getCash).sum();
                payHistoryDto.setCash(cash);
            }

            if (payHistoryDto.getCash() >= totalPrice) {
                reserveHistoryDto.setReserveStatus(ReserveStatus.PAID);
            }

            reserveHistoryDto = save(reserveHistoryDto);
            payHistoryDto.setReserveHistory(reserveHistoryDto);
            payHistoryDto = payHistoryService.save(payHistoryDto);
            return reserveHistoryMapper.toOutputReserveHistory(reserveHistoryDto, cash);
        }else {
            throw new RuntimeException("Already paid!!!");
        }
    }


    @Override
    public ReserveHistoryDto save(ReserveHistoryDto reserveHistoryDto) {
        ReserveHistoryMapper reserveHistoryMapper = new ReserveHistoryMapper();
        return reserveHistoryMapper.toDto(reserveHistoryRepo.save(reserveHistoryMapper.toEntity(reserveHistoryDto)));
    }

    @Override
    public ReserveHistoryDto update(ReserveHistoryDto reserveHistoryDto) {
        return null;
    }

    @Override
    public ReserveHistoryDto findById(Long id) {
        ReserveHistoryMapper reserveHistoryMapper = new ReserveHistoryMapper();
        ReserveHistory reserveHistory = reserveHistoryRepo.findById(id).orElseThrow(()-> new RuntimeException("Айди заказа не найден!"));
        if(reserveHistory.getReserveStatus()==ReserveStatus.CANCELLED){
            throw new RuntimeException("Reserve is cancelled");
        }
        return reserveHistoryMapper.toDto(reserveHistory);
    }

    @Override
    public List<ReserveHistoryDto> findAll() {
        ReserveHistoryMapper reserveHistoryMapper = new ReserveHistoryMapper();
        return reserveHistoryMapper.toDtoList(reserveHistoryRepo.findAll());
    }
}
