import java.util.List;
import java.util.function.Supplier;

public class DataServiceUser implements DataService {
    @Override
    public void saveData(List<String> dataToSave) {

    }

    @Override
    public String getDataById(String id) {
        return null;
    }

    @Override
    public String getDataById(String id, Supplier<String> calculateIfAbsent) {
        return null;
    }

    @Override
    public List<String> getData() {
        return null;
    }

    @Override
    public List<String> getDataListByIds(List<String> idList) {
        return null;
    }

    @Override
    public List<String> getDataByRequest(DataSearchRequest request) {
        return null;
    }
}
