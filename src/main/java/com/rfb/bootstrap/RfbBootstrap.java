package com.rfb.bootstrap;

import com.rfb.domain.*;
import com.rfb.repository.*;
import com.rfb.security.AuthoritiesConstants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.UUID;

/**
 * Created by jt on 10/14/17.
 */
@Component
public class RfbBootstrap implements CommandLineRunner {

    private final RfbLocationRepository rfbLocationRepository;
    private final RfbEventRepository rfbEventRepository;
    private final RfbEventAttendanceRepository rfbEventAttendanceRepository;
    private final PaperRepository paperRepository;
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public RfbBootstrap(RfbLocationRepository rfbLocationRepository, RfbEventRepository rfbEventRepository,
                        RfbEventAttendanceRepository rfbEventAttendanceRepository, PaperRepository paperRepository, PostsRepository postsRepository, UserRepository userRepository,
                        PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.rfbLocationRepository = rfbLocationRepository;
        this.rfbEventRepository = rfbEventRepository;
        this.rfbEventAttendanceRepository = rfbEventAttendanceRepository;
        this.paperRepository = paperRepository;
        this.postsRepository = postsRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }
    @Transactional
    @Override
    public void run(String... strings) throws Exception {

        // init RFB Locations
        if(rfbLocationRepository.count() == 0){
            //only load data if no data loaded
            initData();
        }

    }

    private void initData() {
        User rfbUser = new User();
        rfbUser.setFirstName("Johnny");
        rfbUser.setPassword(passwordEncoder.encode("admin"));
        rfbUser.setLogin("johnny");
        rfbUser.setEmail("johnny@runningforbrews.com");
        rfbUser.setActivated(true);
        rfbUser.addAuthority(authorityRepository.findOne("ROLE_RUNNER"));
        rfbUser.addAuthority(authorityRepository.findOne("ROLE_ORGANIZER"));
        userRepository.save(rfbUser);



        //load data
        RfbLocation aleAndWitch = getRfbLocation("St Pete - Ale and the Witch", DayOfWeek.MONDAY.getValue());

        rfbUser.setHomeLocation(aleAndWitch);
        userRepository.save(rfbUser);

        RfbEvent aleEvent = getRfbEvent(aleAndWitch);

        getRfbEventAttendance(rfbUser, aleEvent);

        RfbLocation ratc = getRfbLocation("St Pete - Right Around The Corner", DayOfWeek.TUESDAY.getValue());

        RfbEvent ratcEvent = getRfbEvent(ratc);

        getRfbEventAttendance(rfbUser, ratcEvent);

        RfbLocation stPeteBrew = getRfbLocation("St Pete - St Pete Brewing", DayOfWeek.WEDNESDAY.getValue());

        RfbEvent stPeteBrewEvent = getRfbEvent(stPeteBrew);

        getRfbEventAttendance(rfbUser, stPeteBrewEvent);

        RfbLocation yardOfAle = getRfbLocation("St Pete - Yard of Ale", DayOfWeek.THURSDAY.getValue());

        RfbEvent yardOfAleEvent = getRfbEvent(yardOfAle);

        getRfbEventAttendance(rfbUser, yardOfAleEvent);

        RfbLocation pourHouse = getRfbLocation("Tampa - Pour House", DayOfWeek.MONDAY.getValue());
        RfbLocation macDintons = getRfbLocation("Tampa - Mac Dintons", DayOfWeek.TUESDAY.getValue());

        RfbLocation satRun = getRfbLocation("Saturday Run for testing", DayOfWeek.SATURDAY.getValue());

        Paper paper = new Paper();
        paper.setId(1L);
        //paper.setAttendanceDate(getRfbEventAttendance(r););
        paper.setRfbEvent(stPeteBrewEvent);
        paper.setUser(rfbUser);
        paper.setPaperName("Test Paper");
        paperRepository.save(paper);

        getPaper(rfbUser, stPeteBrewEvent);

        Posts posts = new Posts();
        posts.setId(1L);
        posts.setTitle("First Post");
        posts.setBody("BODY");
        posts.setPaper(paper);
        postsRepository.save(posts);

        getPosts(paper);
    }


    private void getRfbEventAttendance(User rfbUser, RfbEvent rfbEvent) {
        RfbEventAttendance rfbAttendance = new RfbEventAttendance();
        rfbAttendance.setRfbEvent(rfbEvent);
        rfbAttendance.setUser(rfbUser);
        rfbAttendance.setAttendanceDate(LocalDate.now());

        System.out.println(rfbAttendance.toString());

        rfbEventAttendanceRepository.save(rfbAttendance);
        rfbEventRepository.save(rfbEvent);
    }

    private void getPaper(User rfbUser, RfbEvent rfbEvent) {
        Paper paper = new Paper();
        paper.setRfbEvent(rfbEvent);
        paper.setUser(rfbUser);
        paper.setAttendanceDate(LocalDate.now());

        System.out.println(paper.toString());

        paperRepository.save(paper);
        rfbEventRepository.save(rfbEvent);
    }

    private void getPosts(Paper paper) {
        Posts posts= new Posts();
        posts.setPaper(paper);
        posts.setTitle("First Title");
        posts.setBody("Body");

        System.out.println(posts.toString());

        postsRepository.save(posts);
        paperRepository.save(paper);
    }

    private RfbEvent getRfbEvent(RfbLocation rfbLocation) {
        RfbEvent rfbEvent = new RfbEvent();
        rfbEvent.setEventCode(UUID.randomUUID().toString());
        rfbEvent.setEventDate(LocalDate.now()); // will not be on assigned day...
        rfbLocation.addRvbEvent(rfbEvent);
        rfbLocationRepository.save(rfbLocation);
        rfbEventRepository.save(rfbEvent);
        return rfbEvent;
    }

    private RfbLocation getRfbLocation(String locationName, int value) {
        RfbLocation rfbLocation = new RfbLocation();
        rfbLocation.setLocationName(locationName);
        rfbLocation.setRunDayOfWeek(value);
        rfbLocationRepository.save(rfbLocation);
        return rfbLocation;
    }
}
