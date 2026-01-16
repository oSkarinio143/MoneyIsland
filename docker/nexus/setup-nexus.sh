#!/bin/sh

# --- KONFIGURACJA ---
NEXUS_HOST="https://nexus:8444"
REPO_NAME="moneyisland"
ADMIN_USER="admin"
ADMIN_PASS="1234"

echo "--- START NEXUS PROVISIONING ---"

# 1. Czekamy na Nexusa
# DODANO FLAGĘ -k
echo "⏳ Oczekiwanie na uruchomienie Nexusa ($NEXUS_HOST)..."
until curl -s -k -f -o /dev/null "$NEXUS_HOST/service/rest/v1/status"; do
  echo -n "."
  sleep 5
done
echo ""
echo "✅ Nexus jest online!"

# 2. Sprawdzamy czy repo istnieje
# DODANO FLAGĘ -k
HTTP_CODE=$(curl -s -k -o /dev/null -w "%{http_code}" -u "$ADMIN_USER:$ADMIN_PASS" "$NEXUS_HOST/service/rest/v1/repositories/maven/hosted/$REPO_NAME")

if [ "$HTTP_CODE" -eq 200 ]; then
    echo "✅ Repozytorium '$REPO_NAME' już istnieje. Pomijam."
    exit 0
elif [ "$HTTP_CODE" -eq 401 ]; then
    echo "❌ Błąd autoryzacji! Hasło niepoprawne."
    exit 1
else
    echo "⚙️ Repozytorium '$REPO_NAME' nie istnieje. Tworzę..."

    # 3. Tworzymy repozytorium
    # DODANO FLAGĘ -k
    RESPONSE=$(curl -s -k -w "\nHTTP_STATUS:%{http_code}" -u "$ADMIN_USER:$ADMIN_PASS" -X POST "$NEXUS_HOST/service/rest/v1/repositories/maven/hosted" \
      -H "Content-Type: application/json" \
      -d '{
        "name": "'"$REPO_NAME"'",
        "online": true,
        "storage": {
          "blobStoreName": "default",
          "strictContentTypeValidation": true,
          "writePolicy": "ALLOW"
        },
        "maven": {
          "versionPolicy": "SNAPSHOT",
          "layoutPolicy": "STRICT",
          "contentDisposition": "INLINE"
        }
      }')

    if echo "$RESPONSE" | grep -q "HTTP_STATUS:201"; then
        echo "✅ Sukces! Repozytorium utworzone."
    else
        echo "❌ Błąd tworzenia repozytorium!"
        echo "$RESPONSE"
        exit 1
    fi
fi