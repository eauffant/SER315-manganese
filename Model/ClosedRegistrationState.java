package Model;
public class ClosedRegistrationState implements RegistrationState {
    @Override
    public void openRegistration(RaceEvent race) {
        race.setRegistrationState(new OpenRegistrationState());
    }

    @Override
    public void closeRegistration(RaceEvent race){
         //throw new IllegalStateException("Registration is already closed."); // need to implement catch
    }

    @Override
    public boolean register(){
        return false;
    }
}
