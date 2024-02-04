import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.models.Flight;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;

public class FlightTest {
    LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);

    @Test
    public void checkFilteringPastTime() {
        List<Flight> flights = Arrays.asList(
                FlightBuilder.createFlight(threeDaysFromNow.minusDays(4), threeDaysFromNow.minusDays(4).plusHours(2)),
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)));
        List<Flight> filteredFlights = FlightFilter.filteringPastTime(flights);
        Assertions.assertEquals(List.of(
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5))).toString(), filteredFlights.toString());
    }
    @Test
    public void checkFilteringDepartureBeforeArrival() {
        List<Flight> flights = Arrays.asList(
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(2)));
        List<Flight> filteredFlights = FlightFilter.filteringDepartureBeforeArrival(flights);
        Assertions.assertEquals(List.of(
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2))).toString(),filteredFlights.toString());
    }

    @Test
    public void checkFilteringTransferLessTwoHours() {
        List<Flight> flights = Arrays.asList(
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(7), threeDaysFromNow.plusHours(8)),
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4)));
        List<Flight> filteredFlights = FlightFilter.filteringTransferLessTwoHours(flights);
        Assertions.assertEquals(List.of(
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4))).toString(),filteredFlights.toString());
    }
}
