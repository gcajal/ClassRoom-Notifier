package classroom.notifier.implement;

import java.util.Map;

public interface Filter {

    public void execute(Map<String,String> asignacion);
}
