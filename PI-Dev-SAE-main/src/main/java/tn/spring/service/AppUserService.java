package tn.spring.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.spring.entity.AppUser;
import tn.spring.entity.AppUserRole;
import tn.spring.entity.ConfirmationToken;
import tn.spring.repository.UserRepository;
@Service
@Slf4j
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
	private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
	@Autowired
	UserRepository userRepository ;
	@Autowired
	BCryptPasswordEncoder bcpe ;
	@Autowired
	ConfirmationTokenService cfts ;

	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		AppUser appuser =  userRepository.findByEmail(email)
				.orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG , email )) );
		List<SimpleGrantedAuthority> role= Collections.singletonList(new SimpleGrantedAuthority(appuser.getAppUserRole().name()) ) ;
		return new User(appuser.getEmail(), appuser.getPassword(),appuser.isEnabled(),true,true,true,role);
		
	}
	public String SignUpUser(AppUser appuser) {
		boolean exist = userRepository.findByEmail(appuser.getEmail()).isPresent();
		if (exist) {
			throw new IllegalStateException("email mawjoud ");
		}
		
		String encodedpassword= bcpe.encode(appuser.getPassword());
		appuser.setPassword(encodedpassword);
		appuser.setAppUserRole(AppUserRole.ROLE_CLIENT);
		appuser.setLocked(false);
		appuser.setFirstName(appuser.getFirstname());
		userRepository.save(appuser);
		//send confirmation token
		String token =UUID.randomUUID().toString();
		ConfirmationToken confirmationtoken = new ConfirmationToken(
				token,
				LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15),
				appuser

				);
		cfts.saveConfirmationToken(confirmationtoken); 
		return token; 
	}

	public int enableAppUser(String email) {
		return userRepository.enableAppUser(email);
	}
	public List<AppUser> dddd() {
		return userRepository.findAll();
	}



	 
	 
	public void enableUser(Long id)
	{
		AppUser u =userRepository.findById(id).orElse(null);
		u.setEnabled(false);
		userRepository.save(u);
		
	}
	public void Admin(Long id)
	{
		AppUser u =userRepository.findById(id).orElse(null);
		u.setAppUserRole(AppUserRole.ROLE_ADMIN);
		userRepository.save(u);
		
	}

	/*   public JwtResponse authenticateUser(LoginRequest loginRequest) {

	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = jwtUtils.generateJwtToken(authentication);

	        AppUser userDetails = (AppUser) authentication.getPrincipal();
	        List roles = userDetails.getAuthorities().stream()
	                .map(item -> item.getAuthority())
	                .collect(Collectors.toList());
	        return new JwtResponse(jwt,
	                userDetails.getId(),
	                userDetails.getUsername(),
	                userDetails.getEmail(),
	                roles);
	    }*/
	 	
	 private String buildEmail(Date Daten) {
	        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
	                "\n" +
	                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
	                "\n" +
	                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
	                "    <tbody><tr>\n" +
	                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
	                "        \n" +
	                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
	                "          <tbody><tr>\n" +
	                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
	                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
	                "                  <tbody><tr>\n" +
	                "                    <td style=\"padding-left:10px\">\n" +
	                "                  \n" +
	                "                    </td>\n" +
	                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
	                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Alert Event !!!</span>\n" +
	                "                    </td>\n" +
	                "                  </tr>\n" +
	                "                </tbody></table>\n" +
	                "              </a>\n" +
	                "            </td>\n" +
	                "          </tr>\n" +
	                "        </tbody></table>\n" +
	                "        \n" +
	                "      </td>\n" +
	                "    </tr>\n" +
	                "  </tbody></table>\n" +
	                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
	                "    <tbody><tr>\n" +
	                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
	                "      <td>\n" +
	                "        \n" +
	                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
	                "                  <tbody><tr>\n" +
	                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
	                "                  </tr>\n" +
	                "                </tbody></table>\n" +
	                "        \n" +
	                "      </td>\n" +
	                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
	                "    </tr>\n" +
	                "  </tbody></table>\n" +
	                "\n" +
	                "\n" +
	                "\n" +
	                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
	                "    <tbody><tr>\n" +
	                "      <td height=\"30\"><br></td>\n" +
	                "    </tr>\n" +
	                "    <tr>\n" +
	                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
	                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
	                "        \n" +
	                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + "reported" + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> we are Sorry for this information </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + "" + "\">Activate Now</a> </p></blockquote>\n event reported to "+Daten+ ". <p>See you soon</p>" +
	                "        \n" +
	                "      </td>\n" +
	                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
	                "    </tr>\n" +
	                "    <tr>\n" +
	                "      <td height=\"30\"><br></td>\n" +
	                "    </tr>\n" +
	                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
	                "\n" +
	                "</div></div>";
	    }


}
