package itsm.liquiBaseSample.auditors;

import itsm.liquiBaseSample.domains.AuditRecord;
import itsm.liquiBaseSample.domains.User;
import itsm.liquiBaseSample.security.CurrentUserInfo;
import itsm.liquiBaseSample.services.audit.AuditService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Calendar;

@Service
public class GlobalAuditor implements CustomAuditor {

   private boolean enabled;
   private AuditService auditService;

   public GlobalAuditor( AuditService auditService, boolean enabled) {
      this.enabled = enabled;
      this.auditService = auditService;
   }

   public Object doAudit(String action, Object rootObject, Method method, Object[] args) throws Exception {
      if(!enabled)
         return method.invoke(action, rootObject, method, args);
      Object returnValue;
      User owner = CurrentUserInfo.get();
      try {
         returnValue = method.invoke(rootObject, args);
         auditService.insert(new AuditRecord(action, Calendar.getInstance().getTime(),true, owner));
      }
      catch(Exception ex) {
         auditService.insert(new AuditRecord(action, Calendar.getInstance().getTime(),false, owner));
         throw ex;
      }
      return returnValue;
   }
}
