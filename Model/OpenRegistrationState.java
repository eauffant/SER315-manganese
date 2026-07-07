package Model;
public class OpenRegistrationState implements RegistrationState{

    @Override
    public void openRegistration(RaceEvent race) {
       // throw new IllegalStateException("Registration is already open.");
    }

    @Override
    public void closeRegistration(RaceEvent race){
        race.setRegistrationState(new ClosedRegistrationState());
    }

    @Override
    public boolean register(){
        return true;
    }
}
