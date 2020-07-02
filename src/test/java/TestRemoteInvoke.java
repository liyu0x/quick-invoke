import net.eemo.qi.Light;
import net.eemo.qi.LightHouse;

@LightHouse
public interface TestRemoteInvoke {

    @Light(url = "/get/one")
    String invoke();

}
