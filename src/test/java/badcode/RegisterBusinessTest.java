package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterBusinessTest {
    class xxx implements SpeakerRepository {
        @Override
        public Integer saveSpeaker(Speaker speaker) {
            return 1000;
        }
    }

    // Test to pass
    @Test
    @DisplayName("ไม่ทำการกำหนดชื่อ จะเกิด exception First name is required")
    public void case07() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("test");
        speaker.setLastName("test");
        speaker.setEmail("test@gmail.com");
        int speakerId = registerBusiness.register(new xxx(), speaker);
        assertEquals(1000, speakerId);
    }

    // Test to fail
    @Test
    @DisplayName("ไม่ทำการกำหนดชื่อ จะเกิด exception First name is required")
    public void case01() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            registerBusiness.register(null, new Speaker());
            fail();
        } catch (ArgumentNullException e) {
            if (!e.getMessage().equals("First name is required.")) {
                fail();
            }
        }
    }

    @Test
    @DisplayName("ไม่ทำการกำหนดนามสกุล จะเกิด exception Last name is required")
    public void case02() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("test");
            registerBusiness.register(null, speaker);
            fail();
        } catch (ArgumentNullException e) {
            if (!e.getMessage().equals("Last name is required.")) {
                fail();
            }
        }
    }

    @Test
    @DisplayName("ไม่ทำการกำหนดนามสกุล จะเกิด exception Email is required")
    public void case03() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("test");
            speaker.setLastName("test");
            registerBusiness.register(null, speaker);
            fail();
        } catch (ArgumentNullException e) {
            if (!e.getMessage().equals("Email is required.")) {
                fail();
            }
        }
    }

    @Test
    @DisplayName("email ผิด format")
    public void case04() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("test");
            speaker.setLastName("test");
            speaker.setEmail("test");
            registerBusiness.register(null, speaker);
            fail();
        } catch (DomainEmailInvalidException e) {
        }
    }

    @Test
    @DisplayName("email ไม่อยู่ใน domain")
    public void case05() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("test");
            speaker.setLastName("test");
            speaker.setEmail("test@test.com");
            registerBusiness.register(null, speaker);
            fail();
        } catch (SpeakerDoesntMeetRequirementsException e) {
            if (!e.getMessage().equals("Speaker doesn't meet our standard rules.")) {
                fail();
            }
        }
    }

    @Test
    @DisplayName("save speaker ไม่ได้")
    public void case06() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("test");
            speaker.setLastName("test");
            speaker.setEmail("test@gmail.com");
            registerBusiness.register(null, speaker);
            fail();
        } catch (SaveSpeakerException e) {
            if (!e.getMessage().equals("Can't save a speaker.")) {
                fail();
            }
        }
    }

}