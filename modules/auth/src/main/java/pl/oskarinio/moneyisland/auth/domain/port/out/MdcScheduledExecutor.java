package pl.oskarinio.moneyisland.auth.domain.port.out;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public interface MdcScheduledExecutor {
    ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit);
}
