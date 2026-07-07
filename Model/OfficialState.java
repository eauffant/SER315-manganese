package Model;
public class OfficialState implements OfficialityState {
    @Override
    public boolean requireLicense(){
        return true;
    }
}
