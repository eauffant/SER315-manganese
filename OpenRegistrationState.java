public class OpenRegistrationState implements RegistrationState{

    @Override
    public void openRegistration(Race race) {
       // throw new IllegalStateException("Registration is already open."); // need to implement catch
    }

    @Override
    public void closeRegistration(Race race){
        race.setRegistrationState(new ClosedRegistrationState());
    }

    @Override
    public boolean register(){
        return true;
    }
}
